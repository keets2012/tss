package com.njit.util;
import java.io.BufferedInputStream;  
import java.io.File;  
import java.io.IOException;  
import java.io.InputStream;  
import java.util.Random;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
  
import com.artofsolving.jodconverter.DocumentConverter;  
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;  
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;  
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;  
  
/** 
 * doc docx格式转换 
 */  
public class DocConverter {  
    private static final int environment = 1;// 环境 1：windows 2:linux  
    private String fileString;// (只涉及pdf2swf路径问题)  
    private String outputPath = "";// 输入路径 ，如果不设置就输出在默认的位置  
    private String fileName; 
    private String swfTemp;
    private File pdfFile;  
    private File swfFile;  
    private File docFile;
      
    public DocConverter(String fileString) {  
    	System.out.println(fileString+"222222");
        ini(fileString);  
    }  
  /*
   * java生成随机数字字母组合
   * 
   */
    public String getStringRandom(int length) {  
        
        String val = "";  
        Random random = new Random();  
          
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
              
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }  
    /** 
     * 重新设置file 
     *  
     * @param fileString 
     */  
    public void setFile(String fileString) {  
        ini(fileString);  
    }  
  
    /** 
     * 初始化 
     *  
     * @param fileString 
     */  
    private void ini(String fileString) { 
    	//int i=1;
        this.fileString = fileString;  
        fileName = fileString.substring(0, fileString.lastIndexOf("."));  
        swfTemp=fileString.substring(0,fileString.lastIndexOf("/") );
        docFile = new File(fileString);  
        pdfFile = new File(fileName + ".pdf");
        //Random random1 = new Random(10000);
       // System.out.println(random1.nextInt());
        swfFile = new File(swfTemp+"/"+getStringRandom(13)+ ".swf");
        System.out.println("11-----"+swfFile.getAbsolutePath());
       // System.out.println("111111111");
    }  
      
    /** 
     * 转为PDF 
     *  
     * @param file 
     */  
    private void doc2pdf() throws Exception {  
        if (docFile.exists()) {  
            if (!pdfFile.exists()) {  
                OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);  
                try {  
                    connection.connect();  
                    DocumentConverter converter = new OpenOfficeDocumentConverter(connection);  
                    converter.convert(docFile, pdfFile);  
                    // close the connection  
                    connection.disconnect();  
                    System.out.println("****pdf转换成功，PDF输出：" + pdfFile.getPath()+ "****");  
                } catch (java.net.ConnectException e) {  
                    e.printStackTrace();  
                    System.out.println("****swf转换器异常，openoffice服务未启动！****");  
                    throw e;  
                } catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {  
                    e.printStackTrace();  
                    System.out.println("****swf转换器异常，读取转换文件失败****");  
                    throw e;  
                } catch (Exception e) {  
                    e.printStackTrace();  
                    throw e;  
                }  
            } else {  
                System.out.println("****已经转换为pdf，不需要再进行转化****");  
            }  
        } else {  
            System.out.println("****swf转换器异常，需要转换的文档不存在，无法转换****");  
        }  
    }  
      
    /** 
     * 转换成 swf 
     */  
    @SuppressWarnings("unused")  
    private void pdf2swf() throws Exception {  
        Runtime r = Runtime.getRuntime();  
        if (!swfFile.exists()) {  
            if (pdfFile.exists()) {  
                if (environment == 1) {// windows环境处理  
                    try {  
                        Process p = r.exec("C:/Program Files (x86)/SWFTools/pdf2swf.exe "+ pdfFile.getPath() + " -o "+ swfFile.getPath() + " -T 9");  
                        System.out.print(loadStream(p.getInputStream()));  
                        System.err.print(loadStream(p.getErrorStream()));  
                        System.out.print(loadStream(p.getInputStream()));  
                        System.err.println("****swf转换成功，文件输出："  
                                + swfFile.getPath() + "****");  
                        if (pdfFile.exists()) {  
                            pdfFile.delete();  
                        }  
  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                        throw e;  
                    }  
                } else if (environment == 2) {// linux环境处理  
                    try {  
                        Process p = r.exec("pdf2swf " + pdfFile.getPath()  
                                + " -o " + swfFile.getPath() + " -T 9");  
                        System.out.print(loadStream(p.getInputStream()));  
                        System.err.print(loadStream(p.getErrorStream()));  
                        System.err.println("****swf转换成功，文件输出："  
                                + swfFile.getPath() + "****");  
                        if (pdfFile.exists()) {  
                            pdfFile.delete();  
                        }  
                    } catch (Exception e) {  
                        e.printStackTrace();  
                        throw e;  
                    }  
                }  
            } else {  
                System.out.println("****pdf不存在,无法转换****");  
            }  
        } else {  
            System.out.println("****swf已经存在不需要转换****");  
        }  
    }  
  
    static String loadStream(InputStream in) throws IOException {  
  
        int ptr = 0;  
        in = new BufferedInputStream(in);  
        StringBuffer buffer = new StringBuffer();  
  
        while ((ptr = in.read()) != -1) {  
            buffer.append((char) ptr);  
        }  
  
        return buffer.toString();  
    }  
    /** 
     * 转换主方法 
     */  
    @SuppressWarnings("unused")  
    public boolean conver() {  
  
        if (swfFile.exists()) {  
            System.out.println("****swf转换器开始工作，该文件已经转换为swf****");  
            return true;  
        }  
  
        if (environment == 1) {  
            System.out.println("****swf转换器开始工作，当前设置运行环境windows****");  
        } else {  
            System.out.println("****swf转换器开始工作，当前设置运行环境linux****");  
        }  
        try {  
            doc2pdf();  
            pdf2swf();  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
  
        if (swfFile.exists()) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
  
    /** 
     * 返回文件路径 
     *  
     * @param s 
     */  
    public String getswfPath() {  
        if (swfFile.exists()) {  
            String tempString = swfFile.getPath();
            tempString = tempString.replaceAll("\\\\", "/"); 
            //System.out.println("llppppp"+tempString);
            String swfpath = "http://localhost:8080/Test/uploads"+tempString.substring(tempString.lastIndexOf("/",52));
            //System.out.println("-----"+swfpath);  
            return swfpath;  
        } else {  
            return "";  
        }  
  
    }  
    /** 
     * 设置输出路径 
     */  
    public void setOutputPath(String outputPath) {  
        this.outputPath = outputPath;  
        if (!outputPath.equals("")) {  
            String realName = fileName.substring(fileName.lastIndexOf("/"),  
                    fileName.lastIndexOf("."));  
            if (outputPath.charAt(outputPath.length()) == '/') {  
                swfFile = new File(outputPath + realName + ".swf");  
            } else {  
                swfFile = new File(outputPath + realName + ".swf");  
            }  
        }  
    }
//    public static void main(String s[])
//    {
//        DocConverter d=new DocConverter("D:/Tomcat7/apache-tomcat-7.0.35/webapps/Test/uploads/001/胡润.xls");
//        d.conver();
//    }
}  