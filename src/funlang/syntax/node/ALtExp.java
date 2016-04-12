/* This file was generated by SableCC (http://www.sablecc.org/). */

package funlang.syntax.node;

import funlang.syntax.analysis.*;

@SuppressWarnings("nls")
public final class ALtExp extends PExp
{
    private PArith _left_;
    private TLt _lt_;
    private PArith _right_;

    public ALtExp()
    {
        // Constructor
    }

    public ALtExp(
        @SuppressWarnings("hiding") PArith _left_,
        @SuppressWarnings("hiding") TLt _lt_,
        @SuppressWarnings("hiding") PArith _right_)
    {
        // Constructor
        setLeft(_left_);

        setLt(_lt_);

        setRight(_right_);

    }

    @Override
    public Object clone()
    {
        return new ALtExp(
            cloneNode(this._left_),
            cloneNode(this._lt_),
            cloneNode(this._right_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseALtExp(this);
    }

    public PArith getLeft()
    {
        return this._left_;
    }

    public void setLeft(PArith node)
    {
        if(this._left_ != null)
        {
            this._left_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._left_ = node;
    }

    public TLt getLt()
    {
        return this._lt_;
    }

    public void setLt(TLt node)
    {
        if(this._lt_ != null)
        {
            this._lt_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lt_ = node;
    }

    public PArith getRight()
    {
        return this._right_;
    }

    public void setRight(PArith node)
    {
        if(this._right_ != null)
        {
            this._right_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._right_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._left_)
            + toString(this._lt_)
            + toString(this._right_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._left_ == child)
        {
            this._left_ = null;
            return;
        }

        if(this._lt_ == child)
        {
            this._lt_ = null;
            return;
        }

        if(this._right_ == child)
        {
            this._right_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._left_ == oldChild)
        {
            setLeft((PArith) newChild);
            return;
        }

        if(this._lt_ == oldChild)
        {
            setLt((TLt) newChild);
            return;
        }

        if(this._right_ == oldChild)
        {
            setRight((PArith) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
