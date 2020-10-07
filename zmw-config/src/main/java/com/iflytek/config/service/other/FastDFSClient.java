package com.iflytek.config.service.other;

import com.iflytek.common.exception.DeleteFileException;
import com.iflytek.common.exception.DownloadException;
import com.iflytek.common.exception.UploadFileException;
import com.iflytek.common.model.DFSFile;
import com.iflytek.config.service.app.SystemConfigUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FastDFSClient {
    private StorageClient storageClient = FastDFSConfig.getStorageClient();

    private Logger logger = LoggerFactory.getLogger(FastDFSClient.class);
    //默认不删除的图片
    private static List<DFSFile> defaultImageList = null;
    /**
     * 默认图片地址
     */

    public static String FILE_HOST = SystemConfigUtils.getValue("fastdfs.tracker_server") + ":" + SystemConfigUtils.getValue("fastdfs.tracker_http_port");
    /**
     * 默认用户头像
     */
    public static final String DEFAULT_USER_HEADER = "http://" + FILE_HOST + "/group1/M00/00/00/wKg0gFzVKpKAGg0pAAAmRgfHWFk557.jpg";

    /**
     * 默认用户封面 
     */
    public static final String DEFAULT_USER_COVER = "http://" + FILE_HOST + "/group1/M00/00/00/wKg0gFzVK_eAF-TOAAB6wbbmnQw952.jpg";

    public FastDFSClient() {
        defaultImageList = new ArrayList<>();
        defaultImageList.add(getDFSFile(DEFAULT_USER_HEADER));
        defaultImageList.add(getDFSFile(DEFAULT_USER_COVER));
    }

    private String getFileExt(String path) {
        return path.substring(path.lastIndexOf(".") + 1);
    }

    /**
     * @param path 本地上传文件的路径
     * @return host, groupName, path
     * @author zgzhao
     * @describe 上传文件
     */
    public DFSFile uploadFile(String path) throws IOException, MyException {
        String fileIds[];
        //NameValuePair nvp [] = new NameValuePair[]{
        //        new NameValuePair("age", "18"),
        //        new NameValuePair("sex", "male")
        //};
        fileIds = storageClient.upload_file(path, getFileExt(path), null);
        return new DFSFile(FILE_HOST, fileIds[0], fileIds[1]);
    }

    /***
     *
     * @param is 文件流
     * @param filename 上传文件的原始名称
     * @return
     */
    public DFSFile upload(InputStream is, String filename) throws UploadFileException {
        //is.available();//字节数目
        DFSFile dfsFile;
        // 读取流
        byte[] fileBuff = new byte[0];
        try {
            fileBuff = new byte[is.available()];
            int result = is.read(fileBuff, 0, fileBuff.length);
            logger.info("上传结果" + result);
            String[] fileIds = storageClient.upload_file(fileBuff, getFileExt(filename), null);
            dfsFile = new DFSFile(FILE_HOST, fileIds[0], fileIds[1]);
        } catch (MyException | IOException e) {
            throw new UploadFileException(e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return dfsFile;
    }

    /**
     * @param base64 字符串
     * @return
     * @author zgzhao
     * @describe
     */
    public DFSFile uploadBase64(String base64, String filename) throws UploadFileException {
        return upload(new ByteArrayInputStream(Base64.decodeBase64(base64)), filename);
    }

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe MVC文件上传
     */
    public DFSFile uploadFileWithMultipart(MultipartFile file) throws UploadFileException {
        try {
            return upload(file.getInputStream(), file.getOriginalFilename());
        } catch (MyException | IOException e) {
            throw new UploadFileException(e.getMessage());
        }
    }

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 下载文件写到指定路径(本地)
     */
    public void downFile(DFSFile dfsFile) throws DownloadException {
        byte[] b = new byte[0];
        try {
            b = storageClient.download_file(dfsFile.getGroupName(), dfsFile.getPath());
            IOUtils.write(b, new FileOutputStream("D:/" + UUID.randomUUID().toString() + "." + getFileExt(dfsFile.getPath())));
        } catch (MyException | IOException e) {
            throw new DownloadException(e.getMessage());
        }
    }

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 下载文件
     */
    public void download(DFSFile dfsFile, HttpServletResponse response) throws DownloadException {
        try {
            byte[] content = storageClient.download_file(dfsFile.getGroupName(), dfsFile.getPath());
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(content); // 输出数据
            outputStream.flush();
            outputStream.close();
        } catch (MyException | IOException e) {
            throw new DownloadException(e.getMessage());
        }
    }

    /**
     * @param dfsFile
     * @return 0 表示删除成功
     * @author zgzhao
     * @describe 删除文件
     */
    public int deleteFile(DFSFile dfsFile) throws DeleteFileException {
        if (defaultImageList.contains(dfsFile)) return 1;
        try {
            return storageClient.delete_file(dfsFile.getGroupName(), dfsFile.getPath());
        } catch (MyException | IOException | NullPointerException e ) {
            throw new DeleteFileException(e.getMessage());
        }
    }

    /**
     * @param dfsFile
     * @return 集合对象 nvp.getName() , nvp.getValue()
     * @author zgzhao
     * @describe 获取Mate信息
     */
    public NameValuePair[] getFileMate(DFSFile dfsFile) throws IOException, MyException {
        return storageClient.get_metadata(dfsFile.getGroupName(), dfsFile.getPath());
    }

    /**
     * @param dfsFile
     * @return fileInfo.getSourceIpAddr(), fileInfo.getFileSize(),
     * fileInfo.getCreateTimestamp(),fileInfo.getCrc32()
     * @author zgzhao
     * @describe 返回文件信息
     */
    public FileInfo getFileInfo(DFSFile dfsFile) throws IOException, MyException {
        return storageClient.get_file_info(dfsFile.getGroupName(), dfsFile.getPath());
    }

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 生成地址字符串
     */
    public String getUrl(DFSFile dfsFile) {
        String address = null;
        if (dfsFile != null) {
            address = "http://" + dfsFile.getHost() + "/" + dfsFile.getGroupName() + "/" + dfsFile.getPath();
        }
        return address;
    }

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 根据url路径生成DFSFile对象
     */
    public DFSFile getDFSFile(String url) {
        DFSFile dfsFile = new DFSFile();
        try {
            String host = url.split("/")[2];
            String groupName = url.split("/")[3];
            String path = url.substring(url.indexOf(groupName) + groupName.length() + 1);
            dfsFile.setHost(host);
            dfsFile.setGroupName(groupName);
            dfsFile.setPath(path);
        } catch (Exception e) {
        }
        return dfsFile;
    }
}
