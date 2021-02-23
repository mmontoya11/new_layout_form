package com.perspective.inspeccionzapopan02;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.perspective.inspeccionzapopan02.Beans.Infraccion;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Infraccion infraccion;
    private final int REQUEST_CODE_ASK_PERMISSIONS =111;
    private File infraccionPDF;
    private Button btnImprimirInfraccion;
    private Date horaInicio, horaFin;
    private TextInputEditText
            et_NombreVisitado,
            et_NumeroIdVisitado,
            et_Calle,
            et_Ext, et_Int,
            et_Condominio,
            et_NombreTestigo1,
            et_numeroIdTestigo1;

    private AutoCompleteTextView
            sp_tipoIdVisitado,
            sp_ManifiestaSerVisitado,
            sp_RealizaInspecPeticion,
            sp_fraccionamiento,
            sp_Zona,
            sp_UsoSuelo,
            sp_UsoSueloDetalle,
            sp_tipoIdTestigo1;

    private RadioGroup rg_PropiedadDe;
    Context context;
    String[] COUNTRIES = new String[] {"Item 1", "Item 2", "Item 3", "Item 4", "Otem algo licencia algo proq ue siia s lsfona  siodjfa  asdjfa sfa sdofia fdsldfjoas fofj aosj fiasdjf osajfoiasj foias " };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        et_NombreVisitado = findViewById(R.id.et_nombre_visitado);
        sp_tipoIdVisitado = findViewById(R.id.sp_identifica_con1);
        et_NumeroIdVisitado = findViewById(R.id.et_numero_id_visitado);
        sp_ManifiestaSerVisitado = findViewById(R.id.sp_manifiesta_ser);
        rg_PropiedadDe = findViewById(R.id.radiogoup_propiedad_de);
        sp_RealizaInspecPeticion = findViewById(R.id.sp_inspeccion_peticion_de);

        et_Calle = findViewById(R.id.et_calle);
        et_Ext = findViewById(R.id.et_ext);
        et_Int = findViewById(R.id.et_int);
        sp_fraccionamiento = findViewById(R.id.sp_fraccionamiento);
        sp_Zona = findViewById(R.id.sp_zona);
        et_Condominio = findViewById(R.id.et_condominio);
        sp_UsoSuelo = findViewById(R.id.sp_uso_suelo);
        sp_UsoSueloDetalle = findViewById(R.id.sp_uso_suelo_detalle);

        et_NombreTestigo1 = findViewById(R.id.et_nombre_testigo1);
        sp_tipoIdTestigo1 = findViewById(R.id.sp_identifica_con1);
        et_numeroIdTestigo1 = findViewById(R.id.et_numero_id_testigo1);
        btnImprimirInfraccion = findViewById(R.id.btn_imprimir_infraccion);

        horaInicio = Calendar.getInstance().getTime();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.dropdown_menu_popup_item, COUNTRIES);
        sp_tipoIdVisitado.setAdapter(adapter);

        btnImprimirInfraccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ValidarDatos();

                try {
                    //el resultado final de esta funcion es la InfraccionPDF
                    SolicitarPermisos();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void SolicitarPermisos() throws FileNotFoundException, DocumentException {
        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_ASK_PERMISSIONS);
            }else{
                Log.e("createPdfWrapper","SDK version menor a Marsmellow (6.0)");
            }
        }else{
            CrearInfraccionPDF();
        }

    }

    private void ValidarDatos(){
        infraccion = new Infraccion(et_NombreVisitado.getText(),
                et_NumeroIdVisitado.getText(),
                et_Calle.getText(),
                et_Ext.getText(),et_Int.getText(),
                et_Condominio.getText(),
                et_NombreTestigo1.getText(),
                et_numeroIdTestigo1.getText(),
                sp_tipoIdVisitado.getText(),
                sp_ManifiestaSerVisitado.getText(),
                sp_RealizaInspecPeticion.getText(),
                sp_fraccionamiento.getText(),
                sp_Zona.getText(),
                sp_UsoSuelo.getText(),
                sp_UsoSueloDetalle.getText(),
                sp_tipoIdTestigo1.getText());
    }

    private void CrearInfraccionPDF() throws FileNotFoundException, DocumentException {
        Float spacing = 70f;

        Toast.makeText(getApplicationContext(), " crearInfraccionPDF entro", Toast.LENGTH_LONG).show();

        File carpetaInfracciones = new File(Environment.getExternalStorageDirectory()+"/Infracciones");


        if(!carpetaInfracciones.exists()){
            carpetaInfracciones.mkdir();
            Log.i("crearInfraccionPDF", "Se creo la carpeta Infracciones");
        }else{
            Log.i("crearInfraccionPDF", "Ya existia la carpeta Infracciones");
        }

        String PDFname = "ejemplo"+horaInicio.getTime()+".pdf";

        Document document = new Document(PageSize.LEGAL);
        infraccionPDF = new File(carpetaInfracciones.getAbsolutePath(), PDFname);
        OutputStream outputStream = new FileOutputStream(infraccionPDF);
        Font font = new Font(Font.FontFamily.HELVETICA, 9.0f, Font.NORMAL, BaseColor.BLACK);
        PdfWriter.getInstance(document,outputStream);


        document.open();

        Paragraph p= new Paragraph("En la ciudad de Zapopan, Jalisco, siendo las "+horaInicio.getHours() +" horas del día "
                +horaInicio.getDay()+" de " + horaInicio.getMonth()+ " del año "+ horaInicio.getYear()+ ", el suscrito"
                + infraccion.getNombreVisitado() + " Inspector Municipal con clave "+ "34234 " + ", facultado para llevar a cabo la inspección y vigilancia del cumplimiento de los diversos reglamentos y leyes de aplicación municipal por parte de los particulares, mediante y en cumplimiento de la Orden de Visita folio número "
                + " OV/0/0/0/0/2000/00 " + "dictada por el Director de Inspección y Vigilancia de Zapopan, Jalisco, el día " + "0/0/2000/" + " misma que en original exhibo y en copia legible entrego al visitado, "
                + infraccion.getNombreVisitado()+ ", me constituí física y legalmente en "+ "la finca" + " marcada (o) con el número "
                + infraccion.getExt() + " de la calle " + infraccion.getCalle(),font);
        p.setAlignment(Element.ALIGN_JUSTIFIED);

        Paragraph p1=new Paragraph("Se dio inicio a la inspección y verificación en compañía del visitado y los testigos, comentando la prerrogativa que en todo momento tiene de manifestar lo que a su derecho convenga y aportar las pruebas que considere pertinentes, es decir formular observaciones en el transcurso de la inspección de conformidad con el artículo 72 fracción III y 75 de la Ley del Procedimiento Administrativo del Estado de Jalisco. Una vez practicada la diligencia, se le hace saber al visitado los hechos e irregularidades encontrados y que consisten en",font);
        p1.setAlignment(Element.ALIGN_JUSTIFIED);


        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource( this.getResources(), R.drawable.acta_inspeccion);
        bitmap.compress(Bitmap.CompressFormat.JPEG , 100, stream);
        Image img;



        try {

            img = Image.getInstance(stream.toByteArray());
            float width = document.getPageSize().getWidth();
            float height = document.getPageSize().getHeight();
            img.scaleToFit(width, height);
            img.setAbsolutePosition(0, 0);

            document.add(img);

        } catch (IOException e) {
            e.printStackTrace();
        }

        p.setSpacingBefore(spacing);
        document.add(p);
        document.add(p1);
        document.close();

        Log.e("crearInfraccionPDF", "Termino el proceso");
        Toast.makeText(getApplicationContext(), " crearInfraccionPDF salio", Toast.LENGTH_LONG).show();
    }
}
