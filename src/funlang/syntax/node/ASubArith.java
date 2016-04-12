/* This file was generated by SableCC (http://www.sablecc.org/). */

package funlang.syntax.node;

import funlang.syntax.analysis.*;

@SuppressWarnings("nls")
public final class ASubArith extends PArith
{
    private PArith _arith_;
    private TMinus _minus_;
    private PFac _fac_;

    public ASubArith()
    {
        // Constructor
    }

    public ASubArith(
        @SuppressWarnings("hiding") PArith _arith_,
        @SuppressWarnings("hiding") TMinus _minus_,
        @SuppressWarnings("hiding") PFac _fac_)
    {
        // Constructor
        setArith(_arith_);

        setMinus(_minus_);

        setFac(_fac_);

    }

    @Override
    public Object clone()
    {
        return new ASubArith(
            cloneNode(this._arith_),
            cloneNode(this._minus_),
            cloneNode(this._fac_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASubArith(this);
    }

    public PArith getArith()
    {
        return this._arith_;
    }

    public void setArith(PArith node)
    {
        if(this._arith_ != null)
        {
            this._arith_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._arith_ = node;
    }

    public TMinus getMinus()
    {
        return this._minus_;
    }

    public void setMinus(TMinus node)
    {
        if(this._minus_ != null)
        {
            this._minus_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._minus_ = node;
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
            + toString(this._arith_)
            + toString(this._minus_)
            + toString(this._fac_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._arith_ == child)
        {
            this._arith_ = null;
            return;
        }

        if(this._minus_ == child)
        {
            this._minus_ = null;
            return;
        }

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
        if(this._arith_ == oldChild)
        {
            setArith((PArith) newChild);
            return;
        }

        if(this._minus_ == oldChild)
        {
            setMinus((TMinus) newChild);
            return;
        }

        if(this._fac_ == oldChild)
        {
            setFac((PFac) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}