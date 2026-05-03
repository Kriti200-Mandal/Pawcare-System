package com.pawcare.model;

public class Adoption {

	private int adoptionId;
	 private int userId;
	    private int animalId;
	    private String animalName;
	    private String status;
	    private String userName;

       private String image;

	    public int getAdoptionId() {
	        return adoptionId;
	    }

	    public void setAdoptionId(int adoptionId) {
	        this.adoptionId = adoptionId;
	    }

	    public int getUserId() {
	           return userId;
	       }

	       public void setUserId(int userId) {
	           this.userId = userId;
	       }

	       public int getAnimalId() {
	              return animalId;
	          }

	          public void setAnimalId(int animalId) {
	              this.animalId = animalId;
	          }

	          public String getAnimalName() {
	              return animalName;
	          }

	          public void setAnimalName(String animalName) {
	                 this.animalName = animalName;
	             }

	             public String getStatus() {
	                 return status;
	             }

	             public void setStatus(String status) {
	                 this.status = status;
	             }

	             public String getUserName() {
	            	 return userName;
	             }
	         public void setUserName(String userName) {
	             this.userName = userName;
	         }


public String getImage() {
    return image;
}
public void setImage(String image) {
    this.image = image;
}



}
