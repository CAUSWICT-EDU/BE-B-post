package edu.causwict.restapi.repository.utils;

import edu.causwict.restapi.entity.BaseEntity;

import java.util.concurrent.atomic.AtomicLong;

public class GenerateIDUtil<T extends BaseEntity> {

    private final AtomicLong sequence = new AtomicLong(0);

    /**
     * 저장된 Entity의 ID가 비어있다면 새로 부여합니다.
     *
     * @param entity 대상 Entity
     * @return 만약 ID가 비어있다면 새로 ID가 부여된 Entity를 반환합니다. 그렇지 않으면 입력 Entity 그대로 반환합니다.
     */
    public T generateID(T entity) {
        if (entity.getId() == null) {
            entity.setId(sequence.incrementAndGet());
        }
        return entity;
    }
}
