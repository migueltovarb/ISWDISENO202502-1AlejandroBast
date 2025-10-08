
# Sistema de Seguimiento de Mascotas – Veterinaria (Java)

> Implementación en memoria que cumple la HU **HU_006_SISTEMA_SEGUIMIENTO_MASCOTAS_VETERINARIA**: registrar **dueños**, **mascotas** y **controles veterinarios**, consultar **historial** y generar un **resumen** básico por mascota.  
> El modelo usa `documento` como identificador único de **Dueno** y mantiene `tipo` de control como `String`.

---

## 🧭 Índice

1. Arquitectura y diseño  
2. Estructura del proyecto  
3. Requisitos  
4. Cómo compilar y ejecutar  
5. Modelo de dominio  
6. Casos de uso soportados  
7. Reglas de validación  
8. API de clases  
9. Ejemplo de uso  
10. Pruebas sugeridas  
11. Diagrama UML de Clases
12. Extensiones futuras  
13. Licencia

---

## Arquitectura y diseño

- **Capa única en memoria**: no hay base de datos ni frameworks; todo se gestiona con colecciones Java.
- **Objeto Orquestador**: `ClinicaVeterinaria` expone los casos de uso del enunciado.
- **Modelo**:
  - `Dueno(documento, nombre, telefono)`
  - `Mascota(dueno, nombre, especie, edad)` con una lista de `ControlVeterinario`
  - `ControlVeterinario(fecha: LocalDate, tipo: String, observaciones: String)`

Relaciones clave:
- Un **Dueño** puede tener **0..*** mascotas.  
- Una **Mascota** pertenece a **1** dueño y tiene **0..*** controles.  
- **Unicidad**: `(documentoDelDueño, nombreDeLaMascota)` no puede repetirse.

---

## Estructura del proyecto

```
src/
 └─ main/
    └─ java/
       ├─ ClinicaVeterinaria.java
       ├─ Dueno.java
       ├─ Mascota.java
       ├─ ControlVeterinario.java
       └─ Demo.java            # ejemplo de uso por consola
docs/
 └─ uml-clases.png            # coloca aquí tu diagrama
```

---

## Requisitos

- **Java 17** o superior (se usa `java.time.LocalDate`).
- No hay dependencias externas.

---

## Cómo compilar y ejecutar

```bash
# Desde la carpeta raíz del repo
javac -d out src/main/java/*.java
java -cp out Demo
```

> Si prefieres Maven/Gradle, puedes envolver estas clases fácilmente; este repo se mantiene intencionalmente simple.

---

## Modelo de dominio

- **Dueno**
  - Identificador: `documento` (String, único).
  - Datos: `nombre`, `telefono`.
- **Mascota**
  - Datos: `nombre`, `especie`, `edad`.
  - Asociación: `dueno` (obligatorio).
  - `List<ControlVeterinario> controles`.
- **ControlVeterinario**
  - `fecha` (`LocalDate`), `tipo` (`String`), `observaciones` (`String`).
  - ID interno incremental solo para distinguir controles en memoria (no se expone fuera).

---

## Casos de uso soportados

1. **Registrar dueño**  
   `ClinicaVeterinaria.registrarDueno(nombre, documento, telefono)`
2. **Registrar mascota** (asociada a un dueño)  
   `ClinicaVeterinaria.registrarMascota(dueno, nombre, especie, edad)`
3. **Registrar control** para una mascota  
   `ClinicaVeterinaria.registrarControl(mascota, fecha, tipo, obs)`
4. **Consultar historial** de una mascota  
   `ClinicaVeterinaria.historial(mascota)` → `List<ControlVeterinario>`
5. **Resumen por mascota** (opcional, puede derivarse del tamaño de la lista de controles).

---

## Reglas de validación

- **Campos obligatorios**:
  - Dueño: `nombre`, `documento`, `telefono`.
  - Mascota: `dueno`, `nombre`, `especie` (y `edad >= 0`).
  - Control: `fecha`, `tipo`.
- **No duplicar mascotas** con el **mismo nombre para el mismo dueño**.
- **No registrar controles** para **mascotas inexistentes** en la clínica.
- **No duplicar dueños** por `documento`.

---

## API de clases

### `ClinicaVeterinaria`
- `List<Mascota> getPacientes()`
- `List<Dueno> getDuenos()`
- `Dueno registrarDueno(String nombre, String documento, String telefono)`
- `Mascota registrarMascota(Dueno dueno, String nombre, String especie, int edad)`
- `ControlVeterinario registrarControl(Mascota mascota, LocalDate fecha, String tipo, String obs)`
- `List<ControlVeterinario> historial(Mascota mascota)`

### `Dueno`
- `String getDocumento() / setDocumento(String)`
- `String getNombre() / setNombre(String)`
- `String getTelefono() / setTelefono(String)`

### `Mascota`
- `Dueno getDueno() / setDueno(Dueno)`
- `String getNombre() / setNombre(String)`
- `String getEspecie() / setEspecie(String)`
- `int getEdad() / setEdad(int)`
- `List<ControlVeterinario> getControles()`
- `void agregarControl(ControlVeterinario)`

### `ControlVeterinario`
- `int getId()`
- `LocalDate getFecha()`
- `String getTipo()`
- `String getObservaciones() / setObservaciones(String)`

---

## Ejemplo de uso

```java
ClinicaVeterinaria app = new ClinicaVeterinaria();

Dueno ana = app.registrarDueno("Ana Pérez", "CC123", "3001234567");
Mascota fido = app.registrarMascota(ana, "Fido", "Perro", 3);

app.registrarControl(fido, LocalDate.now(), "VACUNA", "Refuerzo anual");
app.registrarControl(fido, LocalDate.now().plusMonths(6), "CHEQUEO", "Control general");

System.out.println(app.historial(fido)); // Lista de controles

// Validaciones:
app.registrarMascota(ana, "Fido", "Gato", 1); // lanza IllegalStateException
```

---

## Pruebas sugeridas

- Registrar dueño duplicado (`documento` repetido) ⇒ falla.  
- Registrar mascota con edad negativa ⇒ falla.  
- Registrar mascota duplicada para mismo dueño ⇒ falla.  
- Registrar control con `tipo` vacío ⇒ falla.  
- Historial vacío cuando no hay controles ⇒ lista vacía.

---

## Diagrama UML de Clases

<img width="856" height="787" alt="image" src="https://github.com/user-attachments/assets/9bf5ad44-de97-4847-87bd-97bb8fa3e67f" />


## Extensiones futuras

- Persistencia (JPA/JDBC) y capa de repositorios.
- API REST (Spring Boot) o CLI interactiva.
- `ResumenMascota` como DTO dedicado.
- Catálogo de `tipo` de control (a futuro podría migrar de `String` a `enum`).
- Búsquedas por `documento`, por nombre de mascota y filtros de fechas.

[![By: AlejandroBast](https://img.shields.io/badge/By-AlejandroBast-6b9cff)](https://github.com/AlejandroBast)

