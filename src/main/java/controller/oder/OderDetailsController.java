package controller.oder;

import model.OderDetails;
import util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class OderDetailsController {
    public boolean addOderDetails(List<OderDetails> oderdetailes) {
        for (OderDetails details:oderdetailes){
           boolean isOderDetailsAdd =addOderDetails(details);
           if (!isOderDetailsAdd){
               return false;
           }
        }
        return true;

    }

    private boolean addOderDetails(OderDetails details) {
        String sql= "Insert Into orderdetail values(?,?,?,?)";
        try {
         return   CrudUtil.execute(sql,
                 details.getOderid(),
                    details.getItemcode(),
                    details.getQty(),
                    details.getDescount()
                    );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
