package br.edu.ifba.inf008.interfaces;

public interface IBook {
    public Boolean isAvailable();
    public void reserve();
    public void unreserve();
}
