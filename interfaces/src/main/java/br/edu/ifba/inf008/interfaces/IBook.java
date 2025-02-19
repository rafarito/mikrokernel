package br.edu.ifba.inf008.interfaces;

public interface IBook {
    public Boolean isAvailable();
    public void reserve();
    public void unreserve();
    public String getTitle();
    public String getAuthor();
    public int getYear();
    public String getIsbn();
    public String getGender();
}
