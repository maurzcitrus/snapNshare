package sns.data;

import sns.model.PhotoInfo;

public class CreatePhotoCommand {

  public PhotoInfo execute() {
    //todo: insert into photo table with user name, image path, comment
    //todo: save photo into /img folder
    PhotoInfo dummy = new PhotoInfo();
    dummy.setImageName("dummy.jpg");
    return dummy;
  }
}
