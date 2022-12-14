package xyz.davidpineiro.jpush.vm.instruction.annotationHelper;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class InstructionTemplate {

    /*
    the file name
     */
    public final String templateName;

    /*
    the entire file contents loaded into a string, my goodness
     */
    public final String templateString;

    public InstructionTemplate(String templateName, String templateString) {
        this.templateName = templateName;
        this.templateString = templateString;
    }

    /*
    replaces every instance of '${key}' to its corresponding 'value' in the valueMapping
     */
    public void process(Map<String, String> valueMapping, Writer writer) throws IOException {
        String templateCopy = templateString;
        for(final String key : valueMapping.keySet()){
            final String value = valueMapping.get(key);

            templateCopy = templateCopy.replace("${" + key + "}",value);
        }
        writer.write(templateCopy);
    }

    @Override
    public String toString() {
        return "InstructionTemplate{" +
                "templateName='" + templateName + '\'' +
                ", templateString='" + templateString + '\'' +
                '}';
    }
}
