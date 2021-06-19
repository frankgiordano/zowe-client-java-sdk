package zosconsole.zosmf;

import java.util.Optional;

public class IssueParms extends ConsoleParms {

    private Optional<String> command = Optional.empty();
    private Optional<String> sysplexSystem = Optional.empty();
    private Optional<String> solicitedKeyword = Optional.empty();
    private Optional<String> async = Optional.empty();

    public Optional<String> getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = Optional.ofNullable(command);
    }

    public Optional<String> getSysplexSystem() {
        return sysplexSystem;
    }

    public void setSysplexSystem(String sysplexSystem) {
        this.sysplexSystem = Optional.ofNullable(sysplexSystem);;
    }

    public Optional<String> getSolicitedKeyword() {
        return solicitedKeyword;
    }

    public void setSolicitedKeyword(String solicitedKeyword) {
        this.solicitedKeyword = Optional.ofNullable(solicitedKeyword);
    }

    public Optional<String> getAsync() {
        return async;
    }

    public void setAsync(String async) {
        this.async = Optional.ofNullable(async);;
    }

    @Override
    public String toString() {
        return "IssueParms{" +
                "command=" + command +
                ", sysplexSystem=" + sysplexSystem +
                ", solicitedKeyword=" + solicitedKeyword +
                ", async=" + async +
                '}';
    }

}
