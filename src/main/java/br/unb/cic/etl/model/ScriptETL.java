package br.unb.cic.etl.model;

import java.util.Objects;

public class ScriptETL {

    private int id;
    private String path;

    public ScriptETL(int id, String path) {
        this.id = id;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScriptETL scriptETL = (ScriptETL) o;
        return id == scriptETL.id && Objects.equals(path, scriptETL.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path);
    }
}
