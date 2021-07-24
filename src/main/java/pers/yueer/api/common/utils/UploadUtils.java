package pers.yueer.api.common.utils;

import java.io.File;

public class UploadUtils {

    //静态资源路径
    public final static String IMG_PATH_PREFIX = "statics/upload/imgs";

    public static File getImgDirFile() {
        //dome是项目名
        String fileDirPath = new String("F:/Files/GraduationProject/All/project/shop/public/images");

        File fileDir = new File(fileDirPath);
        if (!fileDir.exists()) {

            fileDir.mkdirs();
        }
        return fileDir;
    }

}
