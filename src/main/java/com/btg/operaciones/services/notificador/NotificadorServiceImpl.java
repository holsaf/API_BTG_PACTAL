package com.btg.operaciones.services.notificador;

import com.btg.operaciones.dtos.NotificacionDto;
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
    public void notificar(NotificacionDto notificacionDto) {
        if(notificacionDto.getTipoTransaccion().equals("APERTURA")) {
            if (notificacionDto.getTipoNotificacion().equals("email")) {
                emailService.sendEmail(notificacionDto.getCliente().getEmail(), "Apertura de Fondo", "Se ha realizado la apertura del fondo " + notificacionDto.getNombreFondo());
            } else if (notificacionDto.getTipoNotificacion().equals("sms")) {
                smsService.sendSms(notificacionDto.getCliente().getTelefono(), "Se ha realizado la apertura del fondo " + notificacionDto.getNombreFondo());
            }
        } else if (notificacionDto.getTipoTransaccion().equals("CANCELAR")) {
            if (notificacionDto.getTipoNotificacion().equals("email")) {
                emailService.sendEmail(notificacionDto.getCliente().getEmail(), "Cancelacion de Fondo", "Se ha realizado la cancelacion del fondo: " + notificacionDto.getNombreFondo());
            } else if (notificacionDto.getTipoNotificacion().equals("sms")) {
                smsService.sendSms(notificacionDto.getCliente().getTelefono(), "Se ha realizado la cancelacion del fondo: " + notificacionDto.getNombreFondo());

            }

        }
    }
}
