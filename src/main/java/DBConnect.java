import java.sql.*;
import java.util.Properties;

public class DBConnect {

    public static final String DB_URL ="jdbc:postgresql://localhost/university";
    public static final String DB_USER="postgres";
    public static final String DB_PASSWORD="x";

    public static void main(String[] args) throws SQLException {

        Connection connect = null;
        String selectSql = "SELECT * FROM student";
        String insertSql = "INSERT INTO student (student_id, student_name,student_class) VALUES (4,'Aras', 11)";
        String insertSql2 = "INSERT INTO student (student_id, student_name, student_class) VALUES (9, 'Uraz', 12)";
        String preSql = "INSERT INTO student (student_id, student_name, student_class) VALUES (?,?,?)";
        String updateSql = "UPDATE student SET student_name = 'Duygu Deniz' WHERE student_id=1";
        String updateSql2 = "UPDATE student SET student_name= ? WHERE student_id= ? ";
        String deleteSql = "DELETE FROM student WHERE student_id=3 ";
        String deleteSql2 = "DELETE FROM student WHERE student_id = ? ";

        try {
            connect = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement st =  connect.createStatement(); //Veri tabanı bağlantısı üzerinden statement oluşturuldu
            ResultSet data = st.executeQuery(selectSql); //Student içerisindeki veriler ResultSet'e aktarıldı

            while (data.next()) {
//          Tablodan verileri çekmek
                System.out.println("ID: " + data.getInt("student_id"));
                System.out.println("NAME: " + data.getString("student_name"));
                System.out.println("SINIF: " +data.getInt("student_class"));
                System.out.println("********************");
            }
//          Statement üzerinden tabloya veri ekleme
                System.out.println(st.executeUpdate(insertSql));
                System.out.println(st.executeUpdate(insertSql2));

//          PreparedStatement üzerinden tabloya veri ekleme
                PreparedStatement pre = connect.prepareStatement(preSql);
                pre.setInt(1,7);
                pre.setString(2,"Harun");
                pre.setInt(3,10);
                pre.executeUpdate();
                pre.close();

//          Statement üzerinden tabloda veri güncelleme
                Statement st2 = connect.createStatement();
                st2.executeUpdate(updateSql);

//          PreparedStatement üzerinden tabloya veri güncelleme
                PreparedStatement pr = connect.prepareStatement(updateSql2);
                pr.setString(1,"Seçil Elif");
                pr.setInt(2,2);
                pr.executeUpdate();
                pr.close();

//          Statement üzerinden tablodan veri silme
                Statement st3 = connect.createStatement();
                st3.executeUpdate(deleteSql);

//          PreparedStatement üzerinden veri silme
                PreparedStatement pr3= connect.prepareStatement(deleteSql2);
                pr3.setInt(1,2);
                pr3.executeUpdate();
                pr3.close();

               connect.close();

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
