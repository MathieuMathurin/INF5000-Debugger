/* This file was generated by SableCC (http://www.sablecc.org/). */

package funlang.syntax.node;

import funlang.syntax.analysis.*;

@SuppressWarnings("nls")
public final class ASimplePow extends PPow
{
    private PTerm _term_;

    public ASimplePow()
    {
        // Constructor
    }

    public ASimplePow(
        @SuppressWarnings("hiding") PTerm _term_)
    {
        // Constructor
        setTerm(_term_);

    }

    @Override
    public Object clone()
    {
        return new ASimplePow(
            cloneNode(this._term_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASimplePow(this);
    }

    public PTerm getTerm()
    {
        return this._term_;
    }

    public void setTerm(PTerm node)
    {
        if(this._term_ != null)
        {
            this._term_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._term_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._term_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._term_ == child)
        {
            this._term_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._term_ == oldChild)
        {
            setTerm((PTerm) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
