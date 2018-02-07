package edu.union.adt.fsm;

public interface Element
{
    /**
     *
     */
    private int x;
    private int y;
    private String label;
    private boolean state;

    public int getX()
    {
      return this.x
    }

    public void setX(int ex)
    {
      this.x = ex;
    }

    public int getY()
    {
      return this.y
    }

    public void setY(int ye)
    {
      this.y = ye;
    }

    public String getLabel()
    {
      return this.label
    }

    public void setLabel(String labell)
    {
      this.label = labell;
    }

    public boolean isAccepting()
    {
      return this.state
    }

    public void setState(boolean statee)
    {
      this.state = statee;
    }
}
