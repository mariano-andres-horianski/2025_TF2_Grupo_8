package negocio;

import clinica.SingletonClinica;
/**
 * Representa un hilo de sistema que genera el evento temporal "Retorno Automático" para la ambulancia a intervalos fijos.
 *
 * Esta clase implementa Runnable y se ejecuta en un bucle mientras la simulación esté activa, invocando Ambulancia#retornoAutomatico() 
 * cada determinado tiempo.
 *
 * A diferencia de Asociado o Operario, no representa un actor, sino un evento periódico del sistema.
 */
public class RetornoAutomatico implements Runnable {
    private Ambulancia ambulancia;
    private SingletonClinica clinica;

    /**
	 * Construye el generador de eventos de retorno automático.
	 *
	 * @param ambulancia La instancia de la ambulancia sobre la cual actuar.
	 * @param clinica  La instancia del singleton de la clínica, usada para
	 * verificar el estado de la simulación.
	 */
    public RetornoAutomatico(Ambulancia ambulancia, SingletonClinica clinica) {
        this.ambulancia = ambulancia;
        this.clinica = clinica;
    }

    /**
	 * Bucle de ejecución principal del hilo.
	 *
	 * Mientras la simulación esté activa, este método:
	 * 1. Notifica a la clínica (para el log) que se solicita un retorno.
	 * 2. Invoca Ambulancia#retornoAutomatico(), que es un método sincronizado y puede bloquear el hilo si la ambulancia está ocupada.
	 * 3. Pone el hilo en espera (sleep()) durante 5 segundos.
	 *
	 * El hilo finaliza su ejecución (sale del bucle) cuando clinica.isSimulacionActiva() se evalúa como false.
	 * Esto permite una finalización limpia de la simulación.
	 */
    @Override
    public void run() {
        while(clinica.isSimulacionActiva()) {

            clinica.notificarEvento("RETORNO: Retorno automático solicitado");
            ambulancia.retornoAutomatico();
            
            try {
                Thread.sleep(5000); // cada 5 segundos genera el evento temporal
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
