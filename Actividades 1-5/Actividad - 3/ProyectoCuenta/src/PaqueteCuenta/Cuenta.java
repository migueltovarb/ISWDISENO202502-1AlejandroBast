package PaqueteCuenta;

public class Cuenta {
	
    private String id;
    private String nombre;
    private int saldo = 0;

    public Cuenta(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Cuenta(String id, String nombre, int saldo) {
        this.id = id;
        this.nombre = nombre;
        this.saldo = saldo;
    }
    public String getID() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSaldo() {
        return saldo;
    }

    public int abonar(int monto) {
        saldo += monto;
        return saldo;
    }

    public int retirar(int monto) {
        if (monto <= saldo) {
            saldo -= monto;
        } else {
            System.out.println("Fondos insuficientes");
        }
        return saldo;
    }

    public int transferirA(Cuenta otraCuenta, int monto) {
        if (monto <= saldo) {
            this.saldo -= monto;
            otraCuenta.abonar(monto);
        } else {
            System.out.println("Fondos insuficientes para transferir");
        }
        return saldo;
    }

    @Override
    public String toString() {
        return "Cuenta[id=" + id + ", nombre=" + nombre + ", saldo=" + saldo + "]";
    }
}
