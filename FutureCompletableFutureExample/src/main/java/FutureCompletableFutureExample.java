import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class FutureCompletableFutureExample {

    public static void main(String[] args) {

        // Crear una tarea asíncrona que simula cambiar el pasado
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            // Simulamos un cálculo en segundo plano (por ejemplo, cambiar el pasado)
            try {
                Thread.sleep(1000); // Simulamos un retraso en la tarea
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Cambiar el pasado";
        });

        // Encadenar otra tarea que procesa el resultado del futuro anterior
        CompletableFuture<String> future2 = future1.thenApply(cambio -> {
            // Añadimos un mensaje al resultado anterior (modificación del pasado)
            return cambio + " para arreglar el futuro";
        });

        // Crear otra tarea independiente que simula otro cálculo en paralelo
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1500); // Retraso simulado
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Información adicional desde otra línea temporal";
        });

        // Combinar dos futuros: future2 y future3
        CompletableFuture<String> future4 = future2.thenCombine(future3, (cambio, adicional) -> {
            // Combinamos los resultados de las dos tareas
            return cambio + ". " + adicional;
        });

        // Encadenar una tarea final para mostrar el resultado
        CompletableFuture<Void> finalFuture = future4.thenAccept(result -> {
            // Mostramos el resultado final
            System.out.println("Resultado combinado: " + result);
        });

        // Manejo de errores en cualquier parte del pipeline
        finalFuture = finalFuture.exceptionally(error -> {
            // En caso de error, mostramos un mensaje de error
            System.out.println("Ocurrió un error: " + error.getMessage());
            return null;
        });

        // Añadir una acción final, independiente de éxito o error, usando whenComplete
        finalFuture.whenComplete((result, error) -> {
            if (error == null) {
                System.out.println("¡El futuro está a salvo!");
            } else {
                System.out.println("Algo salió mal con el viaje en el tiempo.");
            }
        });

        // Esperamos a que todas las tareas terminen antes de cerrar la aplicación
        try {
            finalFuture.get(); // Espera la terminación del último CompletableFuture
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
