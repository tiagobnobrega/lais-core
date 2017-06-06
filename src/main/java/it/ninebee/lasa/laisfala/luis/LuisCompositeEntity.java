package it.ninebee.lasa.laisfala.luis;

import java.util.List;

import lombok.Data;

@Data
public class LuisCompositeEntity {
    private String parentType;
    private String value;
    private List<LuisCompositeEntityChild> children;
}
