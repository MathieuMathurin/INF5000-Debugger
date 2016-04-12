/* This file was generated by SableCC (http://www.sablecc.org/). */

package funlang.syntax.node;

import funlang.syntax.analysis.*;

@SuppressWarnings("nls")
public final class AMulFac extends PFac
{
    private PFac _fac_;
    private TStar _star_;
    private PPow _pow_;

    public AMulFac()
    {
        // Constructor
    }

    public AMulFac(
        @SuppressWarnings("hiding") PFac _fac_,
        @SuppressWarnings("hiding") TStar _star_,
        @SuppressWarnings("hiding") PPow _pow_)
    {
        // Constructor
        setFac(_fac_);

        setStar(_star_);

        setPow(_pow_);

    }

    @Override
    public Object clone()
    {
        return new AMulFac(
            cloneNode(this._fac_),
            cloneNode(this._star_),
            cloneNode(this._pow_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAMulFac(this);
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

    public TStar getStar()
    {
        return this._star_;
    }

    public void setStar(TStar node)
    {
        if(this._star_ != null)
        {
            this._star_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._star_ = node;
    }

    public PPow getPow()
    {
        return this._pow_;
    }

    public void setPow(PPow node)
    {
        if(this._pow_ != null)
        {
            this._pow_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._pow_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._fac_)
            + toString(this._star_)
            + toString(this._pow_);
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

        if(this._star_ == child)
        {
            this._star_ = null;
            return;
        }

        if(this._pow_ == child)
        {
            this._pow_ = null;
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

        if(this._star_ == oldChild)
        {
            setStar((TStar) newChild);
            return;
        }

        if(this._pow_ == oldChild)
        {
            setPow((PPow) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
