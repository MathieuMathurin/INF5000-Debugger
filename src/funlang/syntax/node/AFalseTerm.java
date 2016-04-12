/* This file was generated by SableCC (http://www.sablecc.org/). */

package funlang.syntax.node;

import funlang.syntax.analysis.*;

@SuppressWarnings("nls")
public final class AFalseTerm extends PTerm
{
    private TFalse _false_;

    public AFalseTerm()
    {
        // Constructor
    }

    public AFalseTerm(
        @SuppressWarnings("hiding") TFalse _false_)
    {
        // Constructor
        setFalse(_false_);

    }

    @Override
    public Object clone()
    {
        return new AFalseTerm(
            cloneNode(this._false_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAFalseTerm(this);
    }

    public TFalse getFalse()
    {
        return this._false_;
    }

    public void setFalse(TFalse node)
    {
        if(this._false_ != null)
        {
            this._false_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._false_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._false_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._false_ == child)
        {
            this._false_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._false_ == oldChild)
        {
            setFalse((TFalse) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
