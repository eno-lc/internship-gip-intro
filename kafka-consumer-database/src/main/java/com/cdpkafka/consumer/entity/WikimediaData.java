package com.cdpkafka.consumer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @Lob annotation is used to map large data value to a database-supported large object type.
 */

@Entity
@Table(name = "wikimedia_recentchange_data")
@Getter
@Setter
public class WikimediaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "oid")
    private byte[] wikiEventData;

}
