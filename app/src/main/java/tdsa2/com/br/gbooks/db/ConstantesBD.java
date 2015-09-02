package tdsa2.com.br.gbooks.db;

/**
 * Created by ricardo on 02/09/2015.
 */
public class ConstantesBD {

    public static final String DB_NAME = "db_gbooks";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "bibliotecas";
    public static final String COLUMN_BIBLIOTECAS_ID = "id";
    public static final String COLUMN_BIBLIOTECAS_NOME = "nome";
    public static final String COLUMN_BIBLIOTECAS_ENDERECO = "endereco";
    public static final String COLUMN_BIBLIOTECAS_LATITUDE = "latitude";
    public static final String COLUMN_BIBLIOTECAS_LONGITUDE = "longitude";
    public static final String SQL_CREATE_TABLE = "create table "+TABLE_NAME+
                "(" +
                    COLUMN_BIBLIOTECAS_ID+" integer primary key autoincrement,"+
                    COLUMN_BIBLIOTECAS_NOME+" text,"+
                    COLUMN_BIBLIOTECAS_ENDERECO+" text,"+
                    COLUMN_BIBLIOTECAS_LATITUDE+" real,"+
                    COLUMN_BIBLIOTECAS_LONGITUDE+" real"+
                ")";
}
