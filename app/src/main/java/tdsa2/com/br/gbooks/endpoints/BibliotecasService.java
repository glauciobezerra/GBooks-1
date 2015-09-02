package tdsa2.com.br.gbooks.endpoints;

import retrofit.Callback;
import retrofit.http.GET;
import tdsa2.com.br.gbooks.model.BibliotecaContainer;

/**
 * Created by pf0803 on 26/08/2015.
 */
public interface BibliotecasService {

    //bibliotecas é o urlPattern configurado no servidor
    //para cair no Servlet que responde com o JSON de
    //bibliotecas

    @GET("/bibliotecas")
    void getBibliotecas(Callback<BibliotecaContainer> callback);

}
