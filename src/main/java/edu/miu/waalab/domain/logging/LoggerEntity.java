package edu.miu.waalab.domain.logging;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "waalab_logging")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoggerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Temporal(value = TemporalType.DATE)
    private Date date;

    @Temporal(value = TemporalType.TIME)
    private Date time;

    private String principle;
    private String operation;

    private LoggerEntity(LoggerEntityBuilder builder) {
        this.date = builder.date;
        this.time = builder.time;
        this.principle = builder.principle;
        this.operation = builder.operation;
    }

    public static class LoggerEntityBuilder {
        private Date date;
        private Date time;
        private String principle;
        private String operation;

        public LoggerEntityBuilder setDate(Date date) {
            this.date = date;
            return this;
        }

        public LoggerEntityBuilder setTime(Date time) {
            this.time = time;
            return this;
        }

        public LoggerEntityBuilder setPrinciple(String principle) {
            this.principle = principle;
            return this;
        }

        public LoggerEntityBuilder setOperation(String operation) {
            this.operation = operation;
            return this;
        }

        public LoggerEntity build() {
            return new LoggerEntity(this);
        }
    }
}


