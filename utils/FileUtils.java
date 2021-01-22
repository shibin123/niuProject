package com.ricefieldpro.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by hujingquan on 2017/8/7.
 */

public final class FileUtils {

    private FileUtils() {
    }

    /**
     * 获取指定文件夹内所有文件大小的和
     *
     * @param file file
     * @return size
     * @throws Exception
     */
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
     *
     * @param size size
     * @return size
     */
    public static String getFormatSize(long size) {

        if (size < 1024) {
            return size + "Byte";
        }

        double kiloByte = size / 1024.00;
        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);

        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    /**
     * 删除指定目录下的文件，这里用于缓存的删除
     *
     * @param filePath       filePath
     * @param deleteThisPath deleteThisPath
     */
    public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {
                    File[] files = file.listFiles();
                    for (File file1 : files) {
                        deleteFolderFile(file1.getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {
                        file.delete();
                    } else {
                        if (file.listFiles().length == 0) {
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public static boolean copyFile(File srcFile, File destFile) {
        if (!srcFile.exists() || !srcFile.isFile()) {
            return false;
        }
        if (destFile.exists()) {
            destFile.delete();
        }
        FileChannel srcChannel = null;
        FileChannel destChannel = null;
        try {
            destFile.createNewFile();
            FileInputStream srcStream = new FileInputStream(srcFile);
            FileOutputStream destStream = new FileOutputStream(destFile);
            srcChannel = srcStream.getChannel();
            destChannel = destStream.getChannel();
            destChannel.transferFrom(srcChannel, 0, srcChannel.size());
            srcChannel.close();
            destChannel.close();
        } catch (IOException e) {
            return false;
        } finally {
            try {
                if (srcChannel != null) {
                    srcChannel.close();
                }
                if (destChannel != null) {
                    destChannel.close();
                }
            } catch (IOException e) {
                //ignored
            }
        }
        return true;
    }

    //获取文件夹
    public static File getFolder(String filePath) {
        File file = new File(filePath);
        //如果文件夹不存在，就创建它
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    //获取文件
    public static synchronized File getFile(String filePath) {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                //创建父类文件夹
                getFolder(file.getParent());
                //创建文件
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static long getDirSize(File file) {
        //判断文件是否存在
        if (file.exists()) {
            //如果是目录则递归计算其内容的总大小
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                long size = 0;
                for (File f : children)
                    size += getDirSize(f);
                return size;
            } else {
                return file.length();
            }
        } else {
            return 0;
        }
    }

    public static String getFileSize(long size) {
        if (size <= 0) return "0";
        final String[] units = new String[]{"b", "kb", "M", "G", "T"};
        //计算单位的，原理是利用lg,公式是 lg(1024^n) = nlg(1024)，最后 nlg(1024)/lg(1024) = n。
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        //计算原理是，size/单位值。单位值指的是:比如说b = 1024,KB = 1024^2
        return new DecimalFormat("#,##0.##").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }


    //判断是否挂载了SD卡
    public static boolean isSdCardExist() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        }
        return false;
    }

    //递归删除文件夹下的数据
    public static synchronized void deleteFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) return;

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File subFile : files) {
                String path = subFile.getPath();
                deleteFile(path);
            }
        }
        //删除文件
        file.delete();
    }

    //由于递归的耗时问题，取巧只遍历内部三层

    //获取txt文件
    public static List<File> getTxtFiles(String filePath, int layer) {
        List txtFiles = new ArrayList();
        File file = new File(filePath);

        //如果层级为 3，则直接返回
        if (layer == 3) {
            return txtFiles;
        }

        //获取文件夹
        File[] dirs = file.listFiles(
                pathname -> {
                    if (pathname.isDirectory() && !pathname.getName().startsWith(".")) {
                        return true;
                    }
                    //获取txt文件
                    else if (pathname.getName().endsWith(".txt")) {
                        txtFiles.add(pathname);
                        return false;
                    } else {
                        return false;
                    }
                }
        );
        //遍历文件夹
        for (File dir : dirs) {
            //递归遍历txt文件
            txtFiles.addAll(getTxtFiles(dir.getPath(), layer + 1));
        }
        return txtFiles;
    }

    public static String getJsonStr(Context context, String fileName) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();

    }

}
