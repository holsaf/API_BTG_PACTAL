package com.btg.operaciones.services.notificador;

import com.btg.operaciones.models.Notificacion;
import com.btg.operaciones.services.utils.EmailService;
import com.btg.operaciones.services.utils.SmsService;
import org.springframework.stereotype.Service;

@Service
public class NotificadorServiceImpl implements NotificadorService {


    private final SmsService smsService;

    private final EmailService emailService;


    public NotificadorServiceImpl(SmsService smsService, EmailService emailService) {
        this.smsService = smsService;
        this.emailService = emailService;
    }

    @Override
    public void notificar(Notificacion notificacion) {
        if(notificacion.getTipoTransaccion().equals("APERTURA")) {
            if (notificacion.getTipoNotificacion().equals("email")) {
                emailService.sendEmail(notificacion.getCliente().getEmail(), "Apertura de Fondo", "Se ha realizado la apertura del fondo " + notificacion.getNombreFondo());
            } else if (notificacion.getTipoNotificacion().equals("sms")) {
                smsService.sendSms(notificacion.getCliente().getTelefono(), "Se ha realizado la apertura del fondo " + notificacion.getNombreFondo());
            }
        } else if (notificacion.getTipoTransaccion().equals("CANCELAR")) {
            if (notificacion.getTipoNotificacion().equals("email")) {
                emailService.sendEmail(notificacion.getCliente().getEmail(), "Cancelacion de Fondo", "Se ha realizado la cancelacion del fondo: " + notificacion.getNombreFondo());
            } else if (notificacion.getTipoNotificacion().equals("sms")) {
                smsService.sendSms(notificacion.getCliente().getTelefono(), "Se ha realizado la cancelacion del fondo: " + notificacion.getNombreFondo());

            }

        }
    }
}
