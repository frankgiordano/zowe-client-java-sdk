package zosconsole.zosmf;

import java.util.Optional;

public class ConsoleParms {

    private Optional<String> consoleName = Optional.empty();
    private Optional<Boolean> processResponses = Optional.empty();

    public Optional<String> getConsoleName() {
        return consoleName;
    }

    public void setConsoleName(String consoleName) {
        this.consoleName = Optional.ofNullable(consoleName);
    }

    public Optional<Boolean> getProcessResponses() {
        return processResponses;
    }

    public void setProcessResponses(Boolean processResponses) {
        this.processResponses = Optional.ofNullable(processResponses);;
    }

    @Override
    public String toString() {
        return "ConsoleParms{" +
                "consoleName=" + consoleName +
                ", processResponses=" + processResponses +
                '}';
    }

}
