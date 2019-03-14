package mx.ipn.escom.prueba.coffeeapp.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mx.ipn.escom.prueba.coffeeapp.entities.ProductoLocal;
import mx.ipn.escom.prueba.coffeeapp.services.ProductosService;
import retrofit2.Response;

public class GetProductosAsyncTask extends AsyncTask<String, Void, Void>{
    private List<ProductoLocal> productosLocalList;
    private ProductosService productosService;
    private Integer progress;

    public GetProductosAsyncTask(ProductosService productosService){
        this.productosService = productosService;
        this.productosLocalList = new ArrayList<>();
    }

    public List<ProductoLocal> getProductosLocalList(){
        return productosLocalList;
    }

    @Override
    protected Void doInBackground(String... strings) {
        Response<List<ProductoLocal>> response;
        try{
            response = productosService.getProductosByNombreLocal(strings[0]).execute();
            if(response.isSuccessful()){
                if (response.body() != null){
                    Log.d("--->","SE ENCONTRARON LOS PRODUCTOS");
                    productosLocalList = response.body();
                }else{
                    Log.d("--->","NO EXITEN PRODUCTOS");
                }
            }
            else{
                Log.d("--->","ERROR EN LA CONEXION");
            }

        }
        catch(Exception e){
            e.printStackTrace();
            Log.d("--->","Error en la petición");
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        Log.d("--->","EJECUTANDO CONSULTA");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d("--->","CONSULTA EJECUTADA");
    }
}
