//package wh.fcfz.officecontroller.all.tool;
//
//import com.alibaba.excel.converters.Converter;
//import com.alibaba.excel.metadata.GlobalConfiguration;
//import com.alibaba.excel.metadata.data.CellData;
//import com.alibaba.excel.metadata.property.ExcelContentProperty;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class DateConverter implements Converter<Date> {
//
//    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//    @Override
//    public Class<Date> supportJavaTypeKey() {
//        return Date.class;
//    }
//
//    @Override
//    public CellData<String> convertToExcelData(Date value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
//        if (value == null) {
//            return new CellData<>(null);
//        }
//        return new CellData<>(sdf.format(value));
//    }
//
//    @Override
//    public Date convertToJavaData(CellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
//        if (cellData == null || cellData.getStringValue() == null) {
//            return null;
//        }
//        try {
//            return sdf.parse(cellData.getStringValue());
//        } catch (Exception e) {
//            throw new RuntimeException("Parse date error", e);
//        }
//    }
//}
