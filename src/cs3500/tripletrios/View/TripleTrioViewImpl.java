package cs3500.tripletrios.View;

import cs3500.tripletrios.Model.TripleTrioModel;

public class TripleTrioViewImpl implements TripleTrioView {
    private TripleTrioModel model;
    private Appendable output;

    public TripleTrioViewImpl(TripleTrioModel model, Appendable output) {
        if (model == null || output == null) {
            throw new IllegalArgumentException("model or output cannot be null");
        }
        this.model = model;
        this.output = output;
    }
}
