package com.ogutcenali.rabbitmq.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateBook implements Serializable {

    String name;
    Integer bookno;
    String ISBN;
    List<String> authorname;

    List<String> subjectname;
}
