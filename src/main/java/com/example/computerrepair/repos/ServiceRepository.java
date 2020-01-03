package com.example.computerrepair.repos;

import com.example.computerrepair.domain.Discount;
import com.example.computerrepair.domain.Guarantee;
import com.example.computerrepair.domain.Service;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ServiceRepository extends CrudRepository<Service, Long> {

    @Modifying
    @Transactional
    @Query("update Service s set s.discount = :discountId where s.id = :serviceId")
    void setDiscountByServiceId(@Param("serviceId") Long serviceId, @Param("discountId") Discount discountId);

    @Modifying
    @Transactional
    @Query("update Service s set s.guarantee = :guarantee where s.id = :serviceId")
    void setGuaranteeByServiceId(@Param("serviceId") Long serviceId, @Param("guarantee") Guarantee guarantee);
}
