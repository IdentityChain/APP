package com.project.isc.iscdbserver.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 码表
 */
@Entity
@Table(name = "tb_dict")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Dict implements Serializable {

    private static final long serialVersionUId = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64)
    private String dictId;
    @Column
    private String dictType;
    @Column
    private String dictKey;
    @Column
    private String dictValue;
    @Column
    private String dictNote;

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictNote() {
        return dictNote;
    }

    public void setDictNote(String dictNote) {
        this.dictNote = dictNote;
    }
}