package com.project.isc.iscdbserver.util;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.web.multipart.MultipartFile;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;


/**
 * 
 * 编写实体类
此处注意必须要有空构造函数，否则会报错“对象创建错误”
关于注解@Excel，其他还有@ExcelCollection，@ExcelEntity ，@ExcelIgnore，@ExcelTarget等，此处我们用不到，可以去官方查看更多
属性	类型	类型	说明
name	String	null	列名
needMerge	boolean	fasle	纵向合并单元格
orderNum	String	"0"	列的排序,支持name_id
replace	String[]	{}	值得替换 导出是{a_id,b_id} 导入反过来
savePath	String	"upload"	导入文件保存路径
type	int	1	导出类型 1 是文本 2 是图片,3 是函数,10 是数字 默认是文本
width	double	10	列宽
height	double	10	列高,后期打算统一使用@ExcelTarget的height,这个会被废弃,注意
isStatistics	boolean	fasle	自动统计数据,在追加一行统计,把所有数据都和输出这个处理会吞没异常,请注意这一点
isHyperlink	boolean	false	超链接,如果是需要实现接口返回对象
isImportField	boolean	true	校验字段,看看这个字段是不是导入的Excel中有,如果没有说明是错误的Excel,读取失败,支持name_id
exportFormat	String	""	导出的时间格式,以这个是否为空来判断是否需要格式化日期
importFormat	String	""	导入的时间格式,以这个是否为空来判断是否需要格式化日期
format	String	""	时间格式,相当于同时设置了exportFormat 和 importFormat
databaseFormat	String	"yyyyMMddHHmmss"	导出时间设置,如果字段是Date类型则不需要设置 数据库如果是string 类型,这个需要设置这个数据库格式,用以转换时间格式输出
numFormat	String	""	数字格式化,参数是Pattern,使用的对象是DecimalFormat
imageType	int	1	导出类型 1 从file读取 2 是从数据库中读取 默认是文件 同样导入也是一样的
suffix	String	""	文字后缀,如% 90 变成90%
isWrap	boolean	true	是否换行 即支持\n
mergeRely	int[]	{}	合并单元格依赖关系,比如第二列合并是基于第一列 则{1}就可以了
mergeVertical	boolean	fasle	纵向合并内容相同的单元格
 *
 */
public class FileUtil {
	public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
			boolean isCreateHeader, HttpServletResponse response) {
		ExportParams exportParams = new ExportParams(title, sheetName);
		exportParams.setCreateHeadRows(isCreateHeader);
		defaultExport(list, pojoClass, fileName, response, exportParams);

	}

	public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
			HttpServletResponse response) {
		defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
	}

	public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
		defaultExport(list, fileName, response);
	}

	private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response,
			ExportParams exportParams) {
		Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
		if (workbook != null) {
			Sheet sheet = workbook.getSheet("提现记录");
			if (sheet != null) {
				sheet.addValidationData(FileUtil.setBoxs());
			}
		}
			
		downLoadExcel(fileName, response, workbook);
	}

	private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setHeader("content-Type", "application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
		Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
		if (workbook != null)
			;
		downLoadExcel(fileName, response, workbook);
	}

	public static HSSFDataValidation setBoxs() {  
        CellRangeAddressList addressList = new CellRangeAddressList(2, 65535, 11, 11);  
        final String[] DATA_LIST = new String[] { "已打款", "不通过", };  
        DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(DATA_LIST);  
  
        HSSFDataValidation dataValidation = new HSSFDataValidation(addressList, dvConstraint);  
        dataValidation.setSuppressDropDownArrow(false);  
        dataValidation.createPromptBox("输入提示", "请从下拉列表中选择处理结果");  
        dataValidation.setShowPromptBox(true);  
        return dataValidation;  
    }  
	public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
		if (StringUtils.isBlank(filePath)) {
			return null;
		}
		ImportParams params = new ImportParams();
		params.setTitleRows(titleRows);
		params.setHeadRows(headerRows);
		List<T> list = null;
		try {
			list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows,
			Class<T> pojoClass) {
		if (file == null) {
			return null;
		}
		ImportParams params = new ImportParams();
		params.setTitleRows(titleRows);
		params.setHeadRows(headerRows);
		List<T> list = null;
		try {
			list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
		} catch (NoSuchElementException e) {
//			throw new NormalException("excel文件不能为空");
			e.printStackTrace();
		} catch (Exception e) {
//			throw new NormalException(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
}
