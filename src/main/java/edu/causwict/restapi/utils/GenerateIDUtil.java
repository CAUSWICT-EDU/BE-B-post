package edu.causwict.restapi.utils;

import edu.causwict.restapi.entity.BaseEntity;

import java.util.concurrent.atomic.AtomicLong;

public class GenerateIDUtil<T extends BaseEntity> {

    private final AtomicLong sequence = new AtomicLong(0);

    /**
     * 저장된 Entity의 ID가 비어있다면 새로 부여합니다.
     *
     * @param entity 대상 Entity
     */
    public void generateID(T entity) {
        if (entity.getId() == null) {
            entity.setId(sequence.incrementAndGet());
        }
    }
}
