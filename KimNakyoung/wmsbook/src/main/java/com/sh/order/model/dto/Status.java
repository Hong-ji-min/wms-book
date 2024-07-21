package com.sh.order.model.dto;

public enum Status {
    ORDER_CONFIRMING("주문확인중"),        // 주문확인중
    PREPARING_FOR_SHIPMENT("배송준비중"),  // 배송준비중
    SHIPPED("발송완료"),                 // 발송완료
    IN_TRANSIT("배송중"),              // 배송중
    DELIVERED("배송완료"),               // 배송완료
    ORDER_CANCELLED("주문취소");          // 주문취소

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Status fromValue(String value) {
        for (Status status : values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant with value " + value);
    }
}
