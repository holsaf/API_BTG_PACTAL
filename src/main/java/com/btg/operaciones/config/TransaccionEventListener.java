package com.btg.operaciones.config;

import com.btg.operaciones.entities.Transaccion;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import java.util.UUID;

public class TransaccionEventListener extends AbstractMongoEventListener<Transaccion> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Transaccion> event) {

        super.onBeforeConvert(event);
        Transaccion entity = event.getSource();

        if (entity.getUniqueId() == null) {
            entity.setId(UUID.randomUUID());
        }
    }
}
