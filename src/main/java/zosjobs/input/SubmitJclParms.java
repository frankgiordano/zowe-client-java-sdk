package zosjobs.input;

import java.util.Optional;

public class SubmitJclParms {

    private Optional<String> jcl;
    private Optional<String> internalReaderRecfm;
    private Optional<String> internalReaderLrecl;
    private Optional<String> jclSymbols = Optional.empty();

    public SubmitJclParms(String jcl, String internalReaderRecfm, String internalReaderLrecl) {
        this.jcl = Optional.ofNullable(jcl);
        this.internalReaderRecfm = Optional.ofNullable(internalReaderRecfm);
        this.internalReaderLrecl = Optional.ofNullable(internalReaderLrecl);
    }

    public SubmitJclParms(String jcl, String internalReaderRecfm, String internalReaderLrecl, String jclSymbols) {
        this.jcl = Optional.ofNullable(jcl);
        this.internalReaderRecfm = Optional.ofNullable(internalReaderRecfm);
        this.internalReaderLrecl = Optional.ofNullable(internalReaderLrecl);
        this.jclSymbols = Optional.ofNullable(jclSymbols);
    }

    public Optional<String> getJcl() {
        return jcl;
    }

    public void setJcl(String jcl) {
        this.jcl = Optional.ofNullable(jcl);;
    }

    public Optional<String> getInternalReaderRecfm() {
        return internalReaderRecfm;
    }

    public void setInternalReaderRecfm(String internalReaderRecfm) {
        this.internalReaderRecfm = Optional.ofNullable(internalReaderRecfm);;
    }

    public Optional<String> getInternalReaderLrecl() {
        return internalReaderLrecl;
    }

    public void setInternalReaderLrecl(String internalReaderLrecl) {
        this.internalReaderLrecl = Optional.ofNullable(internalReaderLrecl);
    }

    public Optional<String> getJclSymbols() {
        return jclSymbols;
    }

    public void setJclSymbols(String jclSymbols) {
        this.jclSymbols = Optional.ofNullable(jclSymbols);
    }

}
