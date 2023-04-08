package edu.miu.waalab.domain.logging;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "exception_logging")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Temporal(value = TemporalType.DATE)
    private Date date;

    @Temporal(value = TemporalType.TIME)
    private Date time;

    private String principle;
    private String operation;
    private String exceptionType;

    private ExceptionEntity(ExceptionEntityBuilder builder) {
        this.date = builder.date;
        this.time = builder.time;
        this.principle = builder.principle;
        this.operation = builder.operation;
        this.exceptionType = builder.exceptionType;
    }

    public static class ExceptionEntityBuilder {
        private Date date;
        private Date time;
        private String principle;
        private String operation;
        private String exceptionType;

        public ExceptionEntityBuilder setDate(Date date) {
            this.date = date;
            return this;
        }

        public ExceptionEntityBuilder setTime(Date time) {
            this.time = time;
            return this;
        }

        public ExceptionEntityBuilder setPrinciple(String principle) {
            this.principle = principle;
            return this;
        }

        public ExceptionEntityBuilder setOperation(String operation) {
            this.operation = operation;
            return this;
        }

        public ExceptionEntityBuilder setExceptionType(String expType) {
            this.exceptionType = expType;
            return this;
        }


        public ExceptionEntity build() {
            return new ExceptionEntity(this);
        }
    }
}
