/* This file was generated by SableCC (http://www.sablecc.org/). */

package funlang.syntax.node;

import funlang.syntax.analysis.*;

@SuppressWarnings("nls")
public final class ACallTerm extends PTerm
{
    private PCall _call_;

    public ACallTerm()
    {
        // Constructor
    }

    public ACallTerm(
        @SuppressWarnings("hiding") PCall _call_)
    {
        // Constructor
        setCall(_call_);

    }

    @Override
    public Object clone()
    {
        return new ACallTerm(
            cloneNode(this._call_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACallTerm(this);
    }

    public PCall getCall()
    {
        return this._call_;
    }

    public void setCall(PCall node)
    {
        if(this._call_ != null)
        {
            this._call_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._call_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._call_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._call_ == child)
        {
            this._call_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._call_ == oldChild)
        {
            setCall((PCall) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
