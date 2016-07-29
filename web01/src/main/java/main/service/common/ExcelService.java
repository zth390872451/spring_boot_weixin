package main.service.common;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2016/7/28.
 */
public class ExcelService {

    public static final String NONE_VALUE = "none_value";

    /**
     * 读取Excel文件内容
     * @param multipartFile
     * @return
     */
    public static List<Map<Integer,String>> readExcel(MultipartFile multipartFile){
        List<Map<Integer,String>> mapList = new ArrayList<Map<Integer, String>>();

        Workbook workbook = null;
        try {
            //构造工作簿对象
            workbook = Workbook.getWorkbook(multipartFile.getInputStream());
            if (workbook==null)
                return mapList;

            Sheet[] sheets = workbook.getSheets();//表
             for (int i = 0; i < sheets.length;i++){
                 int rowNum = sheets[i].getRows();//行数
                 Map<Integer,String> cellMap = null ;
                 for (int j = 0;j < rowNum; j++){
                     Cell[] cells = sheets[i].getRow(j);//获取该行的所有单元格
                     cellMap  = new HashMap<Integer, String>();
                     for (int k = 0; k < cells.length; k++) {
                        String cellValue = cells[k].getContents().trim();
                         if (StringUtils.isEmpty(cellValue)){
                             cellMap.put(k,null);
                         }else {
                            /* if (k==0 && cellValue.startsWith("file_end")){//当单元格第一个以file_end开头表示文件读取完毕
                                 return mapList;
                             }else {
                                 cellMap.put(k,cellValue.trim());
                             }*/
                             cellMap.put(k,cellValue);
                         }
                     }
                     Collection<String> cellMapValues = cellMap.values();
                     int count = 0;
                     for (String values:cellMapValues) {
                         if (StringUtils.isEmpty(values)){
                             count++;
                         }
                     }
                     if (count==cellMapValues.size()){

                     }else {
                         mapList.add(cellMap);
                     }
                 }
             }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }finally {
            workbook.close();
        }
        return mapList;
    }

}
