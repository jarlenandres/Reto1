package Model;

public class Sucursal {
    private int idSucursal;
    private String nameSucursal;

    public Sucursal() {
        this.idSucursal = 0;
        this.nameSucursal = "";
    }

    public Sucursal(int idSucursal, String nameSucursal) {
        this.idSucursal = idSucursal;
        this.nameSucursal = nameSucursal;
    }
    
    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNameSucursal() {
        return nameSucursal;
    }

    public void setNameSucursal(String nameSucursal) {
        this.nameSucursal = nameSucursal;
    }

    @Override
    public String toString() {
        return getNameSucursal();
    }
}
