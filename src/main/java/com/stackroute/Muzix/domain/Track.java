package com.stackroute.Muzix.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Track")
@PropertySource("application.properties")
public class Track {
    @Id
    @Column(name="id")

    @JsonProperty("id")
    private int id;
    @Column(name="name")
    @JsonProperty("name")
    private String name;
    @Column(name="comment")
    @JsonProperty("artist")
    private String comment;


}