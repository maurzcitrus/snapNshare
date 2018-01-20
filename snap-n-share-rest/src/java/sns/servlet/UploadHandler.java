package sns.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import sns.data.TimelinePhotoBean;
import sns.model.TimelinePhoto;

@WebServlet(name = "UploadHandler", urlPatterns = {"/timeline/*"})
@MultipartConfig
public class UploadHandler extends HttpServlet {
  
  @EJB
  private TimelinePhotoBean timelinePhotoBean;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    final Part filePart = request.getPart("image");
    final String postedFileName = filePart.getName();    
    final String comment = request.getParameter("comment");

    // hard-coded to img folder for demo purpose
    final String path = getServletContext().getRealPath("img");

    String postedDateTime = Long.toString(Calendar.getInstance().getTimeInMillis());

    String newFileName = getUserName(request) + "_" + postedDateTime;
    
    
    String saveTo =  path + File.separator + newFileName;
    String url = request.getServletContext().getContextPath() + "img/" + newFileName;

    OutputStream out = null;
    InputStream filecontent = null;
    PrintWriter writer = null;
    
    try {
      writer = response.getWriter();
      out = new FileOutputStream(new File(saveTo));
      filecontent = filePart.getInputStream();

      int read = 0;
      final byte[] bytes = new byte[1024];

      while ((read = filecontent.read(bytes)) != -1) {
        out.write(bytes, 0, read);
      }

      TimelinePhoto photo = new TimelinePhoto();
      photo.setComment(comment);
      photo.setPostedBy(getUserName(request));
      photo.setImageUrl(url);
      photo.setPostedDateTime(Calendar.getInstance().getTime());
      
      timelinePhotoBean.addPhoto(photo);

      response.setStatus(response.SC_OK);
      response.setContentType("application/json;");
      writer.println(toJson(postedFileName));

    } catch (FileNotFoundException fne) {

      response.setStatus(response.SC_BAD_REQUEST);

    } finally {

      if (out != null) {
        out.close();
      }
      if (filecontent != null) {
        filecontent.close();
      }
      if(writer!=null){
        writer.close();
      }

    }

  }

  @Override
  public String getServletInfo() {
    return "Accepts photo upload requests and saves impages into /img folder";
  }

  private String getFileName(final Part part) {
    final String partHeader = part.getHeader("content-disposition");
    System.out.println(">> Part Header = " + partHeader);

    for (String content : part.getHeader("content-disposition").split(";")) {
      if (content.trim().startsWith("filename")) {
        return content.substring(
            content.indexOf('=') + 1).trim().replace("\"", "");
      }
    }
    return null;
  }

  private String toJson(String fileName) {
    return Json.createObjectBuilder()
        .add("imageName", fileName)
        .build()
        .toString();
  }
  
  private String getUserName(HttpServletRequest request){
    // given "timeline/userName"
    // getPathInfo() should give us "/userName"
    String pathInfo = request.getPathInfo();
    String[] pathParts = pathInfo.split("/");
    String pathSeparator = pathParts[0]; // "/"
    String userName = pathParts[1]; // user name
    System.out.println(" >> username: " + userName);
    
    return userName;
  }
  
  
}
