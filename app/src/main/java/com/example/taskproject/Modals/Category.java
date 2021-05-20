package com.example.taskproject.Modals;


//import com.google.gson.annotations.SerializedName;

public class Category {
    //@SerializedName("id")
    private String mSlno;
  // @SerializedName("title")
    private String mCategory;
   // @SerializedName("image")
    private String mImage;

    public Category(String mSlno, String mCategory, String mImage) {
        this.mSlno = mSlno;
        this.mCategory = mCategory;
       this.mImage = mImage;
    }

    public Category(String category_text, String sl_no) {
    }


    public String getmSlno() {
        return mSlno;
    }

    public void setmSlno(String mSlno) {
        this.mSlno = mSlno;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

   public String getmImage() {
       return mImage;
   }

   public void setmImage(String mImage) {
       this.mImage = mImage;
    }
}



