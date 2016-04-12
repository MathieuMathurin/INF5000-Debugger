/* This file was generated by SableCC (http://www.sablecc.org/). */

package funlang.syntax.node;

import funlang.syntax.analysis.*;

@SuppressWarnings("nls")
public final class ASimpleArith extends PArith
{
    private PFac _fac_;

    public ASimpleArith()
    {
        // Constructor
    }

    public ASimpleArith(
        @SuppressWarnings("hiding") PFac _fac_)
    {
        // Constructor
        setFac(_fac_);

    }

    @Override
    public Object clone()
    {
        return new ASimpleArith(
            cloneNode(this._fac_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASimpleArith(this);
    }

    public PFac getFac()
    {
        return this._fac_;
    }

    public void setFac(PFac node)
    {
        if(this._fac_ != null)
        {
            this._fac_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._fac_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._fac_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._fac_ == child)
        {
            this._fac_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._fac_ == oldChild)
        {
            setFac((PFac) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
