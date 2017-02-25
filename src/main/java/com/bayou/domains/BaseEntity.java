package com.bayou.domains;

import javax.persistence.*;

/**
 * File: BaseEntity
 * Package: com.bayou.domains
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() { return version; }

    public void setVersion(Long version) { this.version = version; }
}
