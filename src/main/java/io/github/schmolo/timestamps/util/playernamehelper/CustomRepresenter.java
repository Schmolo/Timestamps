package io.github.schmolo.timestamps.util.playernamehelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;


/*
This is a CustomRepresenter that fixes two problems
1: That the Order of the Yaml File was always sorted alphabetically instead of with the fields
2: well the values that were null would be also written to the file

Fixes Links:
1st: https://stackoverflow.com/a/60092387/13305911
2nd: https://stackoverflow.com/a/39784914/13305911

Oh yeah btw i dont get any of this code - Tim 03.12.2022
*/
public class CustomRepresenter extends Representer {


    public CustomRepresenter() {
        super();
    }

    public CustomRepresenter(DumperOptions options) {
        super(options);

    }

    @Override
    protected NodeTuple representJavaBeanProperty(Object javaBean, Property property, Object propertyValue, Tag customTag) {
        // if value of property is null, ignore it.
        if (propertyValue == null) {
            return null;
        }
        else {
            return super.representJavaBeanProperty(javaBean, property, propertyValue, customTag);
        }
    }


    protected Set<Property> getProperties(Class<? extends Object> type) {
        Set<Property> propertySet;
        if (typeDefinitions.containsKey(type)) {
            propertySet = typeDefinitions.get(type).getProperties();
        }

        propertySet =  getPropertyUtils().getProperties(type);

        List<Property> propsList = new ArrayList<>(propertySet);
        Collections.sort(propsList, new BeanPropertyComparator());

        return new LinkedHashSet<>(propsList);
    }


    class BeanPropertyComparator implements Comparator<Property> {
        public int compare(Property p1, Property p2) {

            if (p1.getType().getCanonicalName().contains("util") && !p2.getType().getCanonicalName().contains("util")) {
                return 1;
            } else if(p2.getName().endsWith("Name")|| p2.getName().equalsIgnoreCase("name")) {
                return 1;
            } else {
                return -1;
            } // returning 0 would merge keys
        }
    }
}